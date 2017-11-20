select *
  from tagline
 where tagline_id between 1 and 10
   and use_count = (select min(use_count)
                      from tagline)
  order by random()
  limit 1;