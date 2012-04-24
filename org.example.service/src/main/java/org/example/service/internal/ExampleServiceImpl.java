package org.example.service.internal;

import org.example.service.ExampleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements ExampleService {
    // implementation methods go here...

    public String scramble(String text) {
        List<Character> charList = new ArrayList<Character>();

        char[] textChars = text.toCharArray();
        for (char textChar : textChars) {
            charList.add(textChar);
        }

        Collections.shuffle(charList);

        char[] mixedChars = new char[text.length()];
        for (int i = 0; i < mixedChars.length; i++) {
            mixedChars[i] = charList.get(i);
        }

        return new String(mixedChars);
    }
}

