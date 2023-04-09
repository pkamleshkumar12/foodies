package com.ezcom.foodies.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Tag {

    @Id
    private String id;
    private String name;
}
