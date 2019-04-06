package com.example.Todo.domain.exception;

/**
 * データが存在しない場合のエラー
 */
public class NoDataFoundException extends RuntimeException {

    /**
     * コンストラクタ
     */
    public NoDataFoundException(String message){
        super(message);
    }

    /**
     * コンストラクタ
     */
    public NoDataFoundException(Exception e){
        super(e);
    }
}
