create procedure delete_inactive_users(now timestamp)
    language plpgsql
as $$
begin
    delete from user_table ut
    where user_status = 0 and now - ut.creation_date > '2 days'::interval;
    delete from activation_link al
    where used = false and now - al.creation_date > '2 days'::interval;
end;
$$