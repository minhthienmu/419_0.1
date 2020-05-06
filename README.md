# 419_0.1

Phiên bản 0.1:
Để chạy được
- Add lib
- Tạo Database (Microsoft Sql): 
  -Tạo DB User:
    +Tạo bảng: ListUser: username nvarchar(50) - password nvarchar(50)
    +NewQuery:
    CREATE FUNCTION Check_Login(@USERNAME NVARCHAR(50), @PASS NVARCHAR(50))
    RETURNS INT AS
    BEGIN 
	    IF EXISTS(SELECT *FROM dbo.ListUser WHERE username = @USERNAME)
	      BEGIN
		      IF EXISTS(SELECT *FROM dbo.ListUser WHERE username = @USERNAME AND pass = @PASS)
			      RETURN 1
		      ELSE 
			      RETURN -1
	      END
	     RETURN -2
    END
  - Mở port 1433 để kết nối tới Microsoft SQL: Hướng dẫn: https://www.youtube.com/watch?v=BF0DKnhk4Ek
  - Cài telnet - search gg
    
