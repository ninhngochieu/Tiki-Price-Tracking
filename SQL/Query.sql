Tim nhung san pham khong co trong lich su ngay 14
SELECT * FROM `product` WHERE `id` not in (
	SELECT `history`.`id_product` FROM `history` WHERE day(`last_update`) = 14
)