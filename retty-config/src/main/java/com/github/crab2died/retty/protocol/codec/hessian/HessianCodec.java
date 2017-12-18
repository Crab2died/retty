package com.github.crab2died.retty.protocol.codec.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian序列化
 *
 * @author : Crab2Died
 * 2017/12/14  14:57:07
 */
@SuppressWarnings("all")
public class HessianCodec {

    public static byte[] encode(Object obj) throws IOException {

        HessianOutput ho = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            ho = new HessianOutput(baos);
            ho.writeObject(obj);
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (ho != null) {
                ho.close();
            }
        }
    }

    public static <T> T decode(byte[] bytes, Class<T> clazz) throws IOException {

        HessianInput hi = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {

            hi = new HessianInput(bais);
            return (T) hi.readObject(clazz);
        } finally {
            if (hi != null) {
                hi.close();
            }
        }
    }

}
