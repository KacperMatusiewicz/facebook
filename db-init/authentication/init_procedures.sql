create procedure clear_all_expired_sessions(in now timestamp)
    language plpgsql
as $$
begin
    delete from session_entry se
    where now - se.creation_date > '20 minutes'::interval;
    commit;
end
$$;