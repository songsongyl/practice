package org.example.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("music")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
    public class Music {
        @TableId(value = "music_id", type = IdType.AUTO)
        private Integer id;

        @TableField("music_title")
        private String title;

        @TableField("music_author")
        private String author;

        @TableField("music_lyrics")
        private String lyrics;

        @TableField("music_poser")
        private String poster;

        @TableField("music_poster_sign")
        private String posterSign;

        @TableField("music_duration")
        private int duration;

        @TableField("music_playcount")
        private int playCount;

        @TableField("music_description")
        private String description;
    }

