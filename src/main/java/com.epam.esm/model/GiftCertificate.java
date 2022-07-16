package com.epam.esm.model;

import lombok.Data;
import org.postgresql.util.PGInterval;
import sun.jvm.hotspot.utilities.Interval;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class GiftCertificate {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private PGInterval duration;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Set<TagRef> tags;
}
