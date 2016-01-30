//Teacher Registered Courses

select * from courseassignment ca 
 join academiccourse c
on c.CourseTypeId=ca.CourseId
where ca.TeacherId = 1;


//Teacher Unregistered courses
select * from   
              (
                select * from academiccourse
              ) as a where a.CourseTypeId not in (select CourseId  from courseassignment where TeacherId=1 ) ;
