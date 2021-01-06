package com.cnbot.kgrobot;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cnbot.baiduvoice.asr.BaiduRecUtil;
import com.cnbot.baiduvoice.asr.listener.IRecogListener;
import com.cnbot.baiduvoice.asr.util.RecogResult;
import com.cnbot.baiduvoice.speech.wakeup.ISpeechWakeupListener;
import com.cnbot.baiduvoice.speech.wakeup.SpeechWakeUpTool;
import com.cnbot.baiduvoice.utils.BaiduTTS;
import com.cnbot.baiduvoice.wakeup.BaiduWakeUpTool;
import com.cnbot.baiduvoice.wakeup.listener.IBaiduWakeupListener;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    protected BaiduWakeUpTool baiduWakeUpTool;

    protected SpeechWakeUpTool speechWakeUpTool;

    /**
     * 百度语音识别工具类
     */
    private BaiduRecUtil baiduRecUtil = null;

    private BaiduTTS baiduTTS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBaduAsrUtil(MainActivity.this);
        initBaiduTts();
        baiduWakeUpTool = BaiduWakeUpTool.getInstance(MainActivity.this);
//        baiduWakeUpTool.setmWakeuperListener(new IBaiduWakeupListener() {
//            @Override
//            public void wakeupSuccess(String id) {
//                Log.i(TAG, "wakeupSuccess: " + id);
//            }
//
//            @Override
//            public void wakeupFialError(String errorStr) {
//                Log.i(TAG, "wakeupFialError: " + errorStr);
//
//            }
//        });
//        baiduWakeUpTool.initWakeup();

        initWakeUp();
    }


    /**
     * 初始化百度语音识别
     * @param context 上下文
     */
    private void initBaduAsrUtil(Context context) {
        //集成第一步 初始化EventManager类并注册自定义输出事件
        //1.1 初始化：new一个IRecogListener示例 & new 一个 MyRecognizer 示例,并注册输出事件
        baiduRecUtil = BaiduRecUtil.getInstance(context, new BaiduAsrEventListener());
        baiduRecUtil.initASR();

    }


    private void initBaiduTts(){
        baiduTTS = BaiduTTS.getInstanll();
        baiduTTS.init(MainActivity.this);
    }


    private void initWakeUp(){
        speechWakeUpTool = SpeechWakeUpTool.getInstance(MainActivity.this);
        boolean isAuth =  speechWakeUpTool.speechAuth();

        speechWakeUpTool.setiSpeechWakeupListener(new ISpeechWakeupListener() {
                @Override
                public void wakeupSuccess(String id) {
                    Log.i(TAG, "wakeupSuccess: " + id);
                }

                @Override
                public void wakeupFialError(String errorStr) {
                    Log.e(TAG, "wakeupFialError: " + errorStr );
                }

            @Override
            public void wakeupInitSuccess() {

            }

            @Override
            public void wakeupInitFial() {

            }
        });


    }


    public void startWakeUp(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    try {
                        start();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        start();

    }

    public void stotWakeUp(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    try {
                        stop();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        stop();
    }

    // 点击“开始识别”按钮
    // 基于DEMO唤醒词集成第2.1, 2.2 发送开始事件开始唤醒
    private void start() {
   baiduWakeUpTool.startWakeup();
    }

    // 基于DEMO唤醒词集成第4.1 发送停止事件
    protected void stop() {
        baiduWakeUpTool.stopWakeup();
    }

    @Override
    protected void onDestroy() {
        // 基于DEMO唤醒词集成第5 退出事件管理器
        baiduWakeUpTool.release();
        speechWakeUpTool.release();
        super.onDestroy();
    }

    public void stotAsr(View view) {
        baiduRecUtil.stopASR();
    }

    public void startAsr(View view) {
        baiduRecUtil.startASR();
    }

    public void stotTts(View view) {
        baiduTTS.stop();
    }

    public void startTts(View view) {

        baiduTTS.speak("啦地方垃圾地方拉到");
    }


    public void startSppechWakeUp(View view) {
        speechWakeUpTool.startWakeup();
    }

    public void stotSpeechWakeUp(View view) {
        speechWakeUpTool.stopWakeup();
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
        }

        @Override
        public void onAsrBegin() {
            //已经开始说话

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
        }
    }



}