package com.cnbot.kgrobot;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cnbot.baiduvoice.asr.BaiduRecUtil;
import com.cnbot.baiduvoice.asr.listener.IRecogListener;
import com.cnbot.baiduvoice.asr.util.RecogResult;
import com.cnbot.baiduvoice.speech.wakeup.SpeechWakeUpTool;
import com.cnbot.baiduvoice.utils.BaiduTTS;
import com.cnbot.baiduvoice.wakeup.BaiduWakeUpTool;
import com.cnbot.baiduvoice.wakeup.listener.IBaiduWakeupListener;
import com.cnbot.kgrobot.bean.BaseVoiceBean;
import com.cnbot.kgrobot.bean.Msg;
import com.cnbot.kgrobot.bean.Voice;
import com.cnbot.kgrobot.helper.AppHelper;
import com.cnbot.kgrobot.utils.Toaster;
import com.cnbot.kgrobot.voice.presenter.VoicePresenterImpl;
import com.cnbot.baiduvoice.speech.wakeup.ISpeechWakeupListener;
import com.cnbot.kgrobot.voice.view.VoiceView;
import com.cnbot.zlog.Zlog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = VoiceActivity.class.getSimpleName();
    RecyclerView mainRecyclerView;
    Button btnVoiceInput;
    EditText mainInputEdit;
    Button mainSendBt;
    Button mainStopAsrBt;
    private ChatHistoryAdapter adapter;
    //定义变量
    private final List<Msg> history = new ArrayList<>();

    protected BaiduWakeUpTool baiduWakeUpTool;

    protected SpeechWakeUpTool speechWakeUpTool;

    /**
     * 百度语音识别工具类
     */
    private BaiduRecUtil baiduRecUtil = null;

    private BaiduTTS baiduTTS = null;

    private VoicePresenterImpl voicePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        initV();
//        initWakeUp();
        initAsr();
        initTts();
        initP();
        initSpeechWakeUp();
    }

    private void initSpeechWakeUp(){
        speechWakeUpTool = SpeechWakeUpTool.getInstance(VoiceActivity.this);
        boolean isAuth =  speechWakeUpTool.speechAuth();

        speechWakeUpTool.setiSpeechWakeupListener(new ISpeechWakeupListener() {
            @Override
            public void wakeupSuccess(String id) {
                Log.i(TAG, "wakeupSuccess: " + id);
                speechWakeUpTool.stopWakeup();
                startVoice();
            }

            @Override
            public void wakeupFialError(String errorStr) {
                Log.e(TAG, "wakeupFialError: " + errorStr );
            }

            @Override
            public void wakeupInitFial() {

            }

            @Override
            public void wakeupInitSuccess() {
                 speechWakeUpTool.startWakeup();
            }
        });


    }

    private void initP() {
        voicePresenter = new VoicePresenterImpl(AppHelper.getContext(), new VoiceResultView());
    }

    private void initTts() {
        baiduTTS = BaiduTTS.getInstanll();
        baiduTTS.init(VoiceActivity.this);
    }

    private void initAsr() {
        baiduRecUtil = BaiduRecUtil.getInstance(VoiceActivity.this, new BaiduAsrEventListener());
        baiduRecUtil.initASR();
    }

    private void initWakeUp() {
        baiduWakeUpTool = BaiduWakeUpTool.getInstance(VoiceActivity.this);
        baiduWakeUpTool.setmWakeuperListener(new IBaiduWakeupListener() {
            @Override
            public void wakeupSuccess(String id) {
                Log.i(TAG, "wakeupSuccess: " + id);
                startVoice();
            }

            @Override
            public void wakeupFialError(String errorStr) {
                Log.i(TAG, "wakeupFialError: " + errorStr);

            }
        });

    }

    private void sendNlp(String con){
        if(!TextUtils.isEmpty(con)){
            notifyInputMsg(con);
        }
        if(voicePresenter != null){
            voicePresenter.sendVoiceAsk(con);
        }
    }

    private void initV() {
        mainRecyclerView = findViewById(R.id.main_recyclerView);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatHistoryAdapter(this, history);
        mainRecyclerView.setAdapter(adapter);

        btnVoiceInput = findViewById(R.id.btn_voice_input);
        btnVoiceInput.setOnClickListener(this);
        mainInputEdit = findViewById(R.id.main_input_edit);

        mainSendBt = findViewById(R.id.main_send_bt);
        mainSendBt.setOnClickListener(this);

        mainStopAsrBt = findViewById(R.id.main_stop_asr_bt);
        mainStopAsrBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_send_bt:
                String con  = mainInputEdit.getText().toString();
                if(TextUtils.isEmpty(con)){
                    Toaster.showCenterToast("请输入内容");
                    return;
                }
                sendNlp(con);
                break;
            case R.id.btn_voice_input:
                startVoice();
                break;
            case R.id.main_stop_asr_bt:
                baiduRecUtil.stopASR();
                btnVoiceInput.setText("点击说话");
                break;
            default:break;
        }
    }

    /**
     * 在语义分析结束之后，将输入信息更新到界面上，并播放百度tts
     *
     * @param output 灵聚nlp或者讯飞nlp识别结果
     */
    private void notifyOutputMsg(String output) {
        Msg msgBean = new Msg(output, Msg.OUTPUT_TYPE);
        adapter.insertEnd(msgBean);
        mainRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

        btnVoiceInput.setText("点击说话");
      /*  runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                mainRecyclerView.scrollToPosition(history.size() - 1);
            }
        });*/
        baiduTTS.speak(output);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 在语义分析之前，将输入信息更新到界面上
     *
     * @param input 百度asr输入或输入框输入
     */
    private void notifyInputMsg(String input) {
        Msg msgBean = new Msg(input, Msg.INPUT_TYPE);
        adapter.insertEnd(msgBean);
        mainRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

        mainInputEdit.setText("");
//        history.add(new Msg(input, Msg.INPUT_TYPE));
//        adapter.notifyDataSetChanged();
//        mainRecyclerView.scrollToPosition(history.size() - 1);
    }


    /**
     * @descriptoin 开始录音
     * @author dc
     * @date 2018/7/26 10:45
     */
    private void startVoice() {
        baiduTTS.stop();
        baiduRecUtil.startASR();
        btnVoiceInput.setText("开始录音");
    }

    /**
     * P 回调方法
     */
    public class VoiceResultView extends VoiceView {

        @Override
        public void onSuccess(BaseVoiceBean tData) {
            nlpResultHandle(tData);

        }

        @Override
        public void getByPlaceCondNotData() {
            nlpResultHandle(null);
        }

        @Override
        public void requestFail(String msg) {
            nlpResultHandle(null);
        }
    }


    /**
     * 得到语音分析结果之后的操作
     *
     * @param baseVoiceBean
     */
    private void nlpResultHandle(BaseVoiceBean baseVoiceBean) {
        String content = "好吧，你的问题太难了，看来我还需要不断的学习";
        if (baseVoiceBean == null) {
            notifyOutputMsg(content);
            return;
        }
        content = new Gson().toJson(baseVoiceBean, BaseVoiceBean.class);
        btnVoiceInput.setText("思考中...");
        Voice voice = new Gson().fromJson(content, Voice.class);
        Zlog.wtf(TAG, "语义结果 = " + content);
        // 将语义识别结果发送到baseAct。
        notifyOutputMsg(voice.getContent());
    }


    /**
     * ******************************百度语音识别回调start**************************************
     */
    public class BaiduAsrEventListener implements IRecogListener {

        @Override
        public void onAsrReady() {
            //可以开始说话
           /* if (iVoiceEventListener != null) {
                iVoiceEventListener.beforeAsr();
            }*/
            btnVoiceInput.setText("录音中...");
        }

        @Override
        public void onAsrBegin() {
            //已经开始说话
            btnVoiceInput.setText("识别中...");
        }

        @Override
        public void onAsrEnd() {
            //停止说话
          /*  if (iVoiceEventListener != null) {
                iVoiceEventListener.finishAsr();
            }*/
        }

        @Override
        public void onAsrPartialResult(String[] results, RecogResult recogResult) {
            //临时识别结果
            /*if (iVoiceEventListener != null) {
                Zlog.wtf(TAG, "临时识别结果 = " + results[0]);
                iVoiceEventListener.resultProcessAsr(results[0]);
            }*/
        }


        @Override
        public void onAsrFinalResult(String[] results, RecogResult recogResult) {
            //最终识别结果，长语音每一句话会回调一次;不开启长语音，仅回调一次；可能有多个结果，取第一个
          /*  if (iVoiceEventListener != null) {
                Zlog.wtf(TAG, "最终识别结果 = " + results[0]);
                iVoiceEventListener.resultAsr(results[0]);
            }*/
            btnVoiceInput.setText("思考中...");
            sendNlp(results[0]);
            speechWakeUpTool.stopWakeup();
        }

        @Override
        public void onAsrFinish(RecogResult recogResult) {
            //识别结束.可能有多个结果，取第一个

        }

        @Override
        public void onAsrFinishError(int errorCode, int subErrorCode, String descMessage, RecogResult recogResult) {
            //百度识别结果出错
           /* if (iVoiceEventListener != null) {
                iVoiceEventListener.errorAsr(AsrError.getDescFromCode(subErrorCode), subErrorCode);
                Zlog.wtf(TAG, "百度语音识别出错" + descMessage);
            }*/
        }

        @Override
        public void onAsrLongFinish() {
            //长语音
            Log.d(TAG, "百度语音识别开启了长语音，识别结束");
        }


        @Override
        public void onAsrExit() {
            //引擎完成整个识别，空闲中
        }

        @Override
        public void onAsrError(int error, String desc) {
            //百度识别出错
            /*if (iVoiceEventListener != null) {
                iVoiceEventListener.errorAsr(AsrError.getDescFromCode(error), error);
                Zlog.wtf(TAG, "百度语音识别出错" + desc);
            }*/
            Toaster.showCenterToast( "百度语音识别出错" + desc);
            btnVoiceInput.setText("点击说话");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechWakeUpTool.release();
        baiduRecUtil.releaseASR();
        baiduTTS.release();
//        baiduWakeUpTool.release();
    }
}