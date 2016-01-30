select * from academiccourse a inner join courseassignment c on a.CourseTypeId=c.CourseId
inner join user u on c.TeacherId=u.UserId;


select a.CourseTypeId,
       a.Number,
		 a.Name,
		 a.Description,
		 u.FirstName,
		 u.LastName
		  from academiccourse a 
		  inner join courseassignment c on a.CourseTypeId=c.CourseId
        inner join user u on c.TeacherId=u.UserId;