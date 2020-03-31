package javaOffer._4;

import java.io.FileNotFoundException;

/**
 * 描述:
 * Java异常处理机制
 *
 * @author Noah
 * @create 2020-03-30 07:33
 */
public class ErrorAndException {

    private void throwError() {
        throw new StackOverflowError();
    }

    private void throwRunTimeException() {
        throw new RuntimeException();
    }

    private void throwCheckException() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    public static void main(String[] args) throws FileNotFoundException {
        ErrorAndException eae = new ErrorAndException();
        eae.throwError();
        eae.throwRunTimeException();
        eae.throwCheckException();
    }
}
