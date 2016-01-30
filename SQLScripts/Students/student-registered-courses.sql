
select a.CourseTypeId,
     a.Number, 
	  a.Name as CourseName,
	  t.TestId,
	  t.Name as TestName,
	  t.StartDate,
	  t.StartTime,
	  t.EndTime,
	  t.IsActivated from studentcourseregistration sc
 inner join teachercoursetest ct
 on sc.CourseId=ct.CourseId
 inner join test t on
 ct.TestId=t.TestId
inner join academiccourse a on a.CourseTypeId=sc.CourseId
where sc.StudentId=1






