CREATE PROCEDURE sp_org_name_ext
AS
DECLARE @orgid BIGINT
DECLARE @orgname VARCHAR(64)
DECLARE @orgcode VARCHAR(32)
DECLARE @orgnameext VARCHAR(255)
DECLARE cs CURSOR FOR
        SELECT
          T1.ID orgid,T1.NAME as orgname,T1.ORGCODE as orgcode,
          CASE WHEN T2.NAME IS NULL THEN T1.NAME
                WHEN T3.NAME IS NULL AND T2.NAME IS NOT NULL THEN
                T2.NAME +'/' + T1.NAME
                WHEN T4.NAME IS NULL AND T3.NAME IS NOT NULL AND T2.NAME IS NOT NULL THEN
                T3.NAME +'/' + T2.NAME +'/' + T1.NAME
                WHEN T5.NAME IS NULL AND T4.NAME IS NOT NULL AND T3.NAME IS NOT NULL AND T2.NAME IS NOT NULL THEN
                T4.NAME +'/' + T3.NAME +'/' + T2.NAME +'/' + T1.NAME
                WHEN T6.NAME IS NULL AND T5.NAME IS NOT NULL AND T4.NAME IS NOT NULL  AND T3.NAME IS NOT NULL AND T2.NAME IS NOT NULL THEN
                T5.NAME +'/' + T4.NAME +'/' + T3.NAME +'/' + T2.NAME +'/' + T1.NAME
                WHEN T7.NAME IS NULL AND T6.NAME IS NOT NULL AND  T5.NAME IS NOT NULL AND T4.NAME IS NOT NULL  AND T3.NAME IS NOT NULL AND T2.NAME IS NOT NULL THEN
                T6.NAME +'/' + T5.NAME +'/' + T4.NAME +'/' + T3.NAME +'/' + T2.NAME +'/' + T1.NAME
                WHEN T7.NAME IS NOT NULL AND T6.NAME IS NOT NULL  AND  T5.NAME IS NOT NULL AND T4.NAME IS NOT NULL  AND T3.NAME IS NOT NULL AND T2.NAME IS NOT NULL THEN
                T7.NAME +'/' + T6.NAME +'/' + T5.NAME +'/' + T4.NAME +'/' + T3.NAME +'/' + T2.NAME +'/' + T1.NAME
                ELSE '' END     as orgnameext
           FROM dbo.SYS_ORGANIZATION T1
           LEFT JOIN dbo.SYS_ORGANIZATION T2 ON T1.FID = T2.ID
           LEFT JOIN dbo.SYS_ORGANIZATION T3 ON T2.FID = T3.ID
           LEFT JOIN dbo.SYS_ORGANIZATION T4 ON T3.FID = T4.ID
           LEFT JOIN dbo.SYS_ORGANIZATION T5 ON T4.FID = T5.ID
           LEFT JOIN dbo.SYS_ORGANIZATION T6 ON T5.FID = T6.ID
           LEFT JOIN dbo.SYS_ORGANIZATION T7 ON T6.FID = T7.ID
BEGIN
DELETE FROM SYS_ORG_EXT
OPEN cs
FETCH NEXT FROM cs INTO @orgid,@orgname,@orgcode,@orgnameext
   WHILE @@FETCH_STATUS = 0  --fetch语句执行成功返回0，fetch语句执行失败或者此行不在结果集中返回-1，被提取的行不存在则返回-2.
        BEGIN
        PRINT '====start insert===='
        INSERT INTO SYS_ORG_EXT VALUES( @orgid,@orgname,@orgcode,@orgnameext)
        PRINT '==== end  insert===='
        FETCH NEXT FROM cs INTO  @orgid,@orgname,@orgcode,@orgnameext --继续取下一行数据
        END
    CLOSE cs --关闭游标
    DEALLOCATE cs --删除游标
END