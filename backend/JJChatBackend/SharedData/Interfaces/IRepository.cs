using System;
using System.Collections.Generic;

namespace SharedData.Interfaces
{
    public interface IRepository<TEntity>
    {
        IEnumerable<TEntity> GetAll();
        IEnumerable<TEntity> GetAll(Func<TEntity, bool> predicate);

        TEntity GetByID(long id);

        void Insert(TEntity entity);

        void Delete(TEntity entity);

        void Update(TEntity entity);

        void Save();
    }
}