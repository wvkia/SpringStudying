package com.wvkia.tinyioc;

/**
 * @author wukai
 * @date 2019/3/12
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    private String text;


    private OutputService outputService;

    @Override
    public void hello() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
