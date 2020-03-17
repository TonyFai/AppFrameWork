package com.dxtdkwt.zzh.appframework;

import com.dxtdkwt.protobufdemo.Person;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class PersonTest {
    public static void main(String[] args) {
        Person._Person._PhoneNumber.Builder phoneNumberBuilder
                = Person._Person._PhoneNumber.newBuilder()
                .setNumber("13111141349");

        Person._Person.Builder personBuilder
                = Person._Person.newBuilder()
                .setName("续澳辉")
                .setEmail("x13111141349@163.com")
                .setId(19)
                .addPhone(phoneNumberBuilder);

        Person._Person person = personBuilder.build();

        //序列化
        byte[] bytes = person.toByteArray();
        System.out.println("序列化："+Arrays.toString(bytes));
        //反序列话
        try {
            Person._Person personSerialization = Person._Person.parseFrom(bytes);
            System.out.println("反序列化："+personSerialization);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("打印的数据"+person.toString());
    }
}
