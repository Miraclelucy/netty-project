namespace java thrift.generated

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person{
    1:optional String name,
    2:optional int age,
    3:optional boolean married
}

exception MyException{
    1:optional String message,
    2:optional String callBack,
    3:optional String date

}

service PersonService{
     Person getPersonByName(1: required String name) throws (1:MyException myException),
     void savePerson(1: required Person person) throws (1:MyException myException)
}