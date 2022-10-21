package com.mytylor.app.Dto;

public class DressRequestDto {
        public Long id;
        public String name;
        public Long customer_id;
        public Measurements measurements;
        public class Measurements {
            public Long sholder;
            public Long waist;
            public Long belly;
            public Long arm;
            public Long leg;
        }
}


