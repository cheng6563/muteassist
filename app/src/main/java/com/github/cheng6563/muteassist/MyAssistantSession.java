package com.github.cheng6563.muteassist;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;

public class MyAssistantSession extends VoiceInteractionSession {
    public MyAssistantSession(Context context) {
        super(context);
    }

    @Override
    public void onHandleAssist(Bundle data,
                               AssistStructure structure, AssistContent content) {
        super.onHandleAssist(data, structure, content);

        //Handle event
        Intent intent = new Intent("");
        MyApplication.INSTANCE.switchMute(getContext());

    }
}
