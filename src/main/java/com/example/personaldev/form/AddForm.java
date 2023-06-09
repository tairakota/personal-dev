package com.example.personaldev.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddForm {

    @Size(min = 1, max = 10, message = "1～10字の範囲です")
    @NotEmpty(message = "名前を入力してください。")
    private String addName;

    @Size(min = 4, max = 20, message = "4～20字の範囲です")
    @NotEmpty(message = "パスワードを入力してください。")
    private String addPass;
}
