package com.foorun.unieat.client.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PigeonEmailForm implements PigeonForm {
    private List<String> emails;
    private String content;

    public static PigeonEmailForm of(String content, List<String> emails) {
        return new PigeonEmailForm(emails, content);
    }
}
