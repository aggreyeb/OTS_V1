
1.  Teachers Courses
====================================
select a.CourseTypeId , a.Name from courseassignment ca 
inner join academiccourse as a
on ca.courseId=a.CourseTypeId
where ca.TeacherId=1;


2. Course Knowledge Maps

select * from courseknowledgemap c
inner join knowledgemap k
on c.KnowledgeMapId=k.KnowledgeMapId
where c.CourseId=2 and c.AssignBy=1;


select c.CourseId,
c.KnowledgeMapId,k.Name,k.Description,k.Concepts from courseknowledgemap c
inner join knowledgemap k
on c.KnowledgeMapId=k.KnowledgeMapId
where c.CourseId=2 and c.AssignBy=1 ;


3. Teacher-Course-Test List

Select * from Test t 
inner join teachercoursetest ct
where t.TestId=ct.TestId  and ct.CourseId =1 and ct.TeacherId=1


Select t.TestId,
       t.Name,
		 t.TotalMark,
		 t.NumberOfQuestion,
		 t.StartDate,
		 t.StartTime,
		 t.EndTime,
		 t.IsActivated
		 from Test t 
inner join teachercoursetest ct
where t.TestId=ct.TestId  and ct.CourseId =1 and ct.TeacherId=1;

