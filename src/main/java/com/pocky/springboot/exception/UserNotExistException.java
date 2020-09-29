package com.pocky.springboot.exception;

/**
 * @author pocky
 * @date 2020/09/23/0023
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
