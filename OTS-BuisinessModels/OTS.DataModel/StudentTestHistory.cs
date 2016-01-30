using System;

namespace OTS.DataModel
{
    public class StudentTestHistory:Record
    {
        public virtual int TestId { get; set; }
        public virtual int StudentId { get; set; }
        public virtual DateTime StartDate { get; set; }
        public virtual DateTime EndDate { get; set; }
        public virtual float TotalMark { get; set; }
    }
}