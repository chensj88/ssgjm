package cn.com.winning.ssgj.base.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.base.util
 * @date 2018-03-09 15:32
 */
public class VideoUtil {

    public static Long getVideoTime(File file) {
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return m.getDuration();
    }
}
