   update tagline t
      set usage_count = x.new_usage_count
     from (select t.tagline_id, t.usage_count + 1
             from tagline t
            where t.usage_count = (select min(usage_count)
                                     from tagline)
         order by random()
            limit 1) x (id, new_usage_count)
    where t.tagline_id = x.id
returning t.tag;
