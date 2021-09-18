package com.example.MyShop.model;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order {
    LinkedList<Item> items;
    LocalDateTime date;

}
