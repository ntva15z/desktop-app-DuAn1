CREATE DATABASE PROJECT1HH
GO

USE PROJECT1HH
GO
/* Bảng Nhân viên */
IF OBJECT_ID('NHANVIEN') IS NOT NULL
DROP TABLE NHANVIEN
GO
CREATE TABLE NHANVIEN(
MANV NVARCHAR(50) PRIMARY KEY,
TENNV NVARCHAR(30),
SDT NVARCHAR(15),
EMAIL NVARCHAR(50),
/* HAI TRƯỜNG NÀY GIÚP QUẢN LÝ TIỆN LIÊN LẠC*/
MATKHAU VARCHAR(10),
TRANGTHAI bit DEFAULT 1,
VAITRO BIT
)
GO
/* Bảng sản phẩm  */
IF OBJECT_ID('SANPHAM') IS NOT NULL
DROP TABLE SANPHAM
GO
CREATE TABLE SANPHAM(
MASP NVARCHAR(50) PRIMARY KEY,
TENSP NVARCHAR(30),
THUONGHIEU NVARCHAR(30),
LOAISP NVARCHAR(30),
SOLUONG INT,
GIANHAP FLOAT,
GIABAN FLOAT,
/* TRẠNG THÁI CÒN HAY HẾT THÌ MÌNH CÓ THỂ SUY RA TỪ SỐ LƯỢNG HÀNG CÒN SỬ LÍ BẰNG CODE JAVA */
HINH NVARCHAR(50),
GHICHU NVARCHAR(MAX),
)
GO
/* Bảng khách hàng  */
IF OBJECT_ID('KHACHHANG') IS NOT NULL
DROP TABLE KHACHHANG
GO
CREATE TABLE KHACHHANG(
MAKH NVARCHAR(50) PRIMARY KEY,
TENKH NVARCHAR(30),
GIOITINH BIT,
DIACHI NVARCHAR(MAX),
SDT NVARCHAR(15),
EMAIL NVARCHAR(50),
LOAI INT,
GHICHU NVARCHAR(MAX),
)
GO
/* Bảng phiếu nhập */
IF OBJECT_ID('PHIEUNHAP') IS NOT NULL
DROP TABLE PHIEUNHAP
GO
CREATE TABLE PHIEUNHAP(
MAPHIEU NVARCHAR(50) PRIMARY KEY,
MANV NVARCHAR(50) FOREIGN KEY  REFERENCES NHANVIEN NOT NULL ,
NGAYNHAP DATE DEFAULT GETDATE(),
GHICHU NVARCHAR(MAX),
)
GO

/** Bảng phiếu nhập chi tiết */
IF OBJECT_ID('PHIEUNHAPCT') IS NOT NULL
DROP TABLE PHIEUNHAPCT
GO
CREATE TABLE PHIEUNHAPCT(
MAPHIEUCT INT IDENTITY PRIMARY KEY,
MAPHIEU NVARCHAR(50) NOT NULL FOREIGN KEY  REFERENCES PHIEUNHAP,
MASP NVARCHAR(50) NOT NULL FOREIGN KEY  REFERENCES SANPHAM ,
SOLUONG INT DEFAULT 0,
DONGIA FLOAT DEFAULT 0,
)
GO
/* Bảng hóa đơn */
IF OBJECT_ID('HOADON') IS NOT NULL
DROP TABLE HOADON
GO
CREATE TABLE HOADON(
MAHD NVARCHAR(50) PRIMARY KEY,
MANV NVARCHAR(50) FOREIGN KEY  REFERENCES NHANVIEN NOT NULL,
MAKH NVARCHAR(50) FOREIGN KEY  REFERENCES KHACHHANG NOT NULL,
NGAYBAN DATE DEFAULT GETDATE(),
GIAMGIA INT ,
TONGTIEN FLOAT,
GHICHU NVARCHAR(MAX),

)
GO
/* bảng hóa đơn chi tiết */
IF OBJECT_ID('HOADONCT') IS NOT NULL
DROP TABLE HOADONCT
GO
CREATE TABLE HOADONCT(
MAHDCT INT IDENTITY PRIMARY KEY,
MAHD NVARCHAR(50)  FOREIGN KEY REFERENCES HOADON NOT NULL,
MASP NVARCHAR(50) FOREIGN KEY REFERENCES SANPHAM NOT NULL,
SL INT NOT NULL,
DONGIA FLOAT,
TONGTIEN FLOAT,
)

/* nhập liệu */

/* bảng nhân viên */
insert into NHANVIEN values('HoangNH',N'Nguyễn Huy Hoàng','093089172','Hoangnh@gmail.com','123456',1,1),
                           ('DaiNV',N'Nguyễn Văn Đại','0382552922','Dainv@gmail.com','123456',1,1),
						   ('DuLQ',N'Lưu Quỳnh Dư','0615566788','DuLQ@gmail.com','123456',0,1)
/* Bảng Khách Hàng */
insert into KHACHHANG values('kh001',N'Trần Bình Trọng',1,N'Hà Nội','0939283292','Trong@gmail.com',0,N''),
							('kh002',N'Trần Anh Phương',0,N'Hà Nội','093883288','phuong@gmail.com',1,N''),
							('kh003',N'Nguyễn Văn Nam',1,N'Hà Nội','0939283665','nam@gmail.com',2,N''),
							('kh004',N'Nguyễn Thị Yên Nhi',0,N'Hà Nội','0939283999','nhi@gmail.com',3,N''),
							('kh005',N'Trần Quang Vinh',1,N'Hà Nội','0939283898','vinh@gmail.com',1,N'')
/* Bảng sản phẩm */
insert into sanpham values('sp001',N'Áo Len Gucci','Gucci',N'Quần áo mùa đông',90,100000,120000,'aolen.jpg',N'Đồ hiệu'),
						  ('sp002',N'Áo Da Gucci','Gucci',N'Quần áo mùa đông',60,200000,320000,'aoda.jpg',N'Đồ hiệu'),
						  ('sp003',N'Quần đùi LV','LV',N'Quần áo mùa hè',70,1000000,1220000,'quandui.jpg',N'Đồ hiệu'),
						  ('sp004',N'Áo ba lỗ Balenciaga','balenciaga',N'Quần áo mùa hè',50,500000,620000,'aobalo.jpg',N'Đồ hiệu'),
						  ('sp005',N'Tông lào LV','LV',N'Quần áo mùa Hè',90,100000,120000,'tong.jpg',N'Đồ hiệu')
/* bảng hóa đơn*/
insert into hoadon values('HD01','DaiNV','kh001','01/12/2020',0,120000,N'done'),
						 ('HD02','DaiNV','kh002','01/14/2020',5,304000,N'done'),
						 ('HD03','HoangNH','kh003','01/15/2019',10,1098000,N'done'),
						 ('HD04','HoangNH','kh004','01/16/2019',20,496000,N'done'),
						 ('HD05','DuLQ','kh005','01/17/2019',0,120000,N'done')
/* Bảng hóa đơn CT */
insert into HOADONCT values('HD01','sp001',1,120000,120000),
						   ('HD02','sp002',1,320000,304000),
						   ('HD03','sp003',4,1220000,1098000),
						   ('HD04','sp004',1,620000,496000),
						   ('HD05','sp005',10,120000,120000)
/* bảng phiếu nhập */
insert into PHIEUNHAP values('pn001','DaiNV','12/3/2020',''),
							('pn002','DaiNV','1/5/2020',''),
							('pn003','HoangNH','12/9/2019',''),
							('pn004','HoangNH','8/3/2019',''),
							('pn005','DaiNV','12/3/2020','')
/* bảng phiếu nhập chi tiết */
insert into PHIEUNHAPCT values('pn001','sp001',12,100000),
							  ('pn002','sp002',9,200000),
							  ('pn003','sp003',6,1000000),
							  ('pn004','sp004',8,500000),
							  ('pn005','sp005',9,100000)

select * from NHANVIEN
select * from KHACHHANG
select * from SANPHAM
select * from HOADON
select * from HOADONCT
select * from PHIEUNHAP
select * from PHIEUNHAPCT
SELECT MAKH FROM dbo.KHACHHANG
SELECT giaban AS[gia] FROM dbo.SANPHAM WHERE TENSP LIKE N'%Quần đùi LV%'
                              
                      
SELECT SUM(dbo.HOADONCT.TONGTIEN) AS TongTien,dbo.HOADON.MAHD 
FROM dbo.HOADONCT INNER JOIN dbo.HOADON  ON HOADON.MAHD = HOADONCT.MAHD
WHERE dbo.HOADON.MAHD = dbo.HOADONCT.MAHD AND dbo.HOADON.MAHD LIKE '%HD9%'
GROUP BY dbo.HOADON.MAHD

SELECT *
FROM dbo.HOADONCT INNER JOIN dbo.HOADON ON HOADON.MAHD = HOADONCT.MAHD 
WHERE HOADON.MAHD = HOADONCT.MAHD AND HOADON.MAHD = 'HD03'

SELECT TONGTIEN FROM dbo.HOADON WHERE MAKH = 'kh001'

------Proc thông kê phiếu nhập theo năm------------

create proc sp_thongke_phieunhap(@year int)
as begin
select distinct MONTH(pn.NGAYNHAP) as thang,
(select   sum(soluong) from PHIEUNHAPCT
join PHIEUNHAP on PHIEUNHAPCT.MAPHIEU = PHIEUNHAP.MAPHIEU
where MONTH(NGAYNHAP)  = MONTH(pn.NGAYNHAP) and year(NGAYNHAP) =@year
) as tongsoluong,
(select sum(soluong * dongia) from PHIEUNHAPCT
join PHIEUNHAP on PHIEUNHAPCT.MAPHIEU = PHIEUNHAP.MAPHIEU
where MONTH(NGAYNHAP)  = MONTH(pn.NGAYNHAP) and year(NGAYNHAP) =  @year
) as tongtien
from PHIEUNHAP pn join PHIEUNHAPCT pnct on pn.MAPHIEU = pnct.MAPHIEU
where year(pn.ngaynhap) = @year
order by thang desc
end
go
----Proc thống kê nhập hàng chi tiết theo từng tháng------
create proc sp_thongke_phieunhapct(@year int, @month int)
as begin

select distinct pnct.MASP, TENSP,
(select  sum(SOLUONG) from PHIEUNHAPCT where MASP = sp.MASP) 
as soluong,
(select  min(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongianhonhat,
(select  max(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongialonnhat,
(select  avg(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongiaTB
from
PHIEUNHAP pn join PHIEUNHAPCT pnct on pn.MAPHIEU = pnct.MAPHIEU
             join SANPHAM sp on sp.MASP = pnct.MASP
where year(pn.ngaynhap) = @year and month(pn.ngaynhap) = @month

end
go

-------Proc thống kê năm nhập hàng-------
create proc sp_thongke_namPN
as begin
select distinct year(ngaynhap) from PHIEUNHAP
end

exec sp_thongke_phieunhapct 2019,8
go

------Proc thống kê  bán hàng theo năm----------
create proc sp_thongke_hoadon(@year int)
as begin

select distinct month(hd.NGAYBAN) as thang,
(select sum(SL) from HOADON join HOADONCT on HOADON.MAHD = HOADONCT.MAHD
where year(HOADON.NGAYBAN) = @year and MONTH(HOADON.NGAYBAN)= MONTH(hd.NGAYBAN)) as soluongbanra,
(select sum(HOADONCT.TONGTIEN) from HOADON join HOADONCT on HOADON.MAHD = HOADONCT.MAHD
where year(HOADON.NGAYBAN) = @year and MONTH(HOADON.NGAYBAN)= MONTH(hd.NGAYBAN))as tienthuve,

(select sum(sl* HOADONCT.DONGIA)- sum(HOADONCT.TONGTIEN) from HOADON join HOADONCT on HOADON.MAHD = HOADONCT.MAHD
where year(HOADON.NGAYBAN) = @year and MONTH(HOADON.NGAYBAN)= MONTH(hd.NGAYBAN))as giamgia

from HOADON hd join HOADONCT hdct on hd.MAHD = hdct.MAHD
where year(hd.NGAYBAN) = @year
end
go

-------------proc thống kê bán hàng chi tiết----------
create proc sp_thongke_hoadonct(@year int, @month int)
as begin

select distinct hdct.MASP, TENSP,
(select  sum(SOLUONG) from PHIEUNHAPCT where MASP = sp.MASP) 
as soluong,
(select  min(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongianhonhat,
(select  max(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongialonnhat,
(select  avg(DONGIA) from PHIEUNHAPCT where MASP = sp.MASP) as dongiaTB
from
HOADON hd join HOADONCT hdct on hd.MAHD = hdct.MAHD
             join SANPHAM sp on sp.MASP = hdct.MASP
where year(hd.NGAYBAN) = @year and month(hd.NGAYBAN) = @month

end
go
------proc Thong ke nam khach hang------------
create proc sp_thongke_namhd
as begin
select distinct year(ngayban) from HOADON
end



