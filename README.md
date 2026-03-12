# user-json-service

SOA Lab 06-ийн JSON REST Profile Service.

## Товч тайлбар
Энэхүү service нь хэрэглэгчийн профайл мэдээллийн CRUD үйлдлийг хариуцна.

## Ашигласан технологи
- Java 17
- Maven
- Jersey / REST API
- SQLite
- JDBC

## Үндсэн боломжууд
- Profile үүсгэх
- Profile дуудах
- Profile шинэчлэх
- Profile устгах
- User ID-аар profile авах

## Base URL
`http://localhost:8082/users`

## Endpoints

### POST /users
Шинэ профайл үүсгэнэ.

### GET /users/{id}
Профайлыг ID-аар авна.

### PUT /users/{id}
Профайлын мэдээллийг шинэчилнэ.

### DELETE /users/{id}
Профайлыг устгана.

### GET /users/by-user?userId={id}
Хэрэглэгчийн ID-аар профайл авна.

## Authentication
Энэхүү service нь protected endpoint-ууд дээр token шалгадаг.

Authentication header:
```text
Authorization: Bearer <token>
