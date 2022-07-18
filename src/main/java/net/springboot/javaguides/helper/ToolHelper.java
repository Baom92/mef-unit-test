package net.springboot.javaguides.helper;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ToolHelper {
    public String generateId(){
        return UUID.randomUUID().toString();
    }

    public int fibonacci(int n)  {
        if(n == 0 || n == 1){
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
