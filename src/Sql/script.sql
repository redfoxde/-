create table admin
(
    username varchar(255) not null,
    password varchar(255) null
);

create table guests
(
    guest_id      int auto_increment
        primary key,
    guest_name    varchar(255) null,
    guest_gender  varchar(255) null,
    guest_contact varchar(255) null,
    id_number     varchar(255) null,
    check_in_date datetime     null,
    expected_stay int          null
);

create index guest_name
    on guests (guest_name);

create table rooms
(
    room_id       int auto_increment
        primary key,
    room_number   int            null,
    room_type     varchar(255)   null,
    room_price    decimal(10, 2) null,
    room_discount varchar(255)   null,
    STATUS        varchar(255)   null,
    room_manager  varchar(255)   null,
    room_contact  varchar(255)   null,
    constraint room_number_2
        unique (room_number)
);

create table booking
(
    booking_id     int auto_increment
        primary key,
    guest_name     varchar(255) null,
    room_number    int          null,
    check_in_date  datetime     null,
    check_out_date datetime     null,
    constraint booking_ibfk_1
        foreign key (guest_name) references guests (guest_name),
    constraint booking_ibfk_2
        foreign key (room_number) references rooms (room_number)
            on update cascade on delete cascade
);

create index guest_name
    on booking (guest_name);

create definer = root@localhost trigger after_insert_order
    after insert
    on booking
    for each row
begin 
        UPDATE rooms
            SET STATUS='已预定'
        WHERE room_number=NEW.room_number;
    end;


