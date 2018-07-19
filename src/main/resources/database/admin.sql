create trigger updateUserLock before update on users for each row
  begin
    declare user_wrong int;
    select new.PsdWrongTime into user_wrong for update ;
    if user_wrong >=5 then
     set  new.IsLockout = '是';
    end if
  end