package com.example.demo.utils;

import com.example.demo.dto.CursorDTO;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

public class CursorUtil {

    public static String encode(CursorDTO cursor)
    {
        if(cursor == null || cursor.createdAt() == null) return null;

        String createdAt = cursor.createdAt().toString();
        String postId = cursor.questionId();
        String s = createdAt + "|" + postId;

        return Base64.getUrlEncoder().withoutPadding().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    public static CursorDTO decode(String encoded)
    {
        if (encoded == null || encoded.isBlank()) return new CursorDTO(null, null);

        String str = new String(Base64.getUrlDecoder().decode(encoded), StandardCharsets.UTF_8);
        String[] parts = str.split("\\|",2);
        LocalDateTime ts = LocalDateTime.parse(parts[0]);
        String id = parts.length > 1 ? parts[1] : null;
        return new CursorDTO(ts,id);
    }
}
