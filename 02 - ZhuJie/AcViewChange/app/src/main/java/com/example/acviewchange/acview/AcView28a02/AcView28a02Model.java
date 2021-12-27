package com.example.acviewchange.acview.AcView28a02;

import com.example.acviewchange.acview.AcViewBaseModel;
import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewModelAnnotation;

/**
 * @author WWJ
 * @detail:
 * @date: 2021/12/27 16:44
 */
@AcViewModelAnnotation("28a02")
public class AcView28a02Model extends AcViewBaseModel<Integer> {

    private Long lastControlTime = 0L;

    public AcView28a02Model(String mid) {
        super(mid);
    }

    @Override
    public void sendPowCmd(OnRequestListener<Integer> listener) {
        int guess = random.nextInt(200);
        if (guess <= 198) {
            if (pow == 0) {
                long nowTime = System.currentTimeMillis();
                if (nowTime - lastControlTime > 5000) {
                    pow = 1;
                    listener.onOk(pow);
                    lastControlTime = nowTime;
                } else {
                    listener.onFail(new Throwable("Control within 5s"));
                }
            } else if (pow == 1) {
                pow = 0;
                listener.onOk(pow);
            }
        } else {
            listener.onFail(new Throwable("NetWork Error"));
        }
    }
}
