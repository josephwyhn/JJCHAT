using DataAccess.DBContext;
using SharedData.Interfaces;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.EF
{
    class EFRepository<TEntity> : IRepository<TEntity> where TEntity : class
    {
        private DbContext _dbContext;
        private DbSet<TEntity> _dbSet;

        public EFRepository(DbContext dbContext)
        {
            _dbContext = dbContext;
            _dbSet = _dbContext.Set<TEntity>();
        }

        public void Delete(TEntity entity) => _dbSet.Remove(entity);

        public IEnumerable<TEntity> GetAll() => _dbSet.AsEnumerable();

        public IEnumerable<TEntity> GetAll(Func<TEntity, bool> predicate) => _dbSet.Where(predicate);

        public TEntity GetByID(long id) => _dbSet.Find(id);

        public TEntity Insert(TEntity entity) => _dbSet.Add(entity);

        public void Save() => _dbContext.SaveChanges();

        public void Update(TEntity entity)
        {
            _dbSet.Attach(entity);
            _dbContext.Entry(entity).State = EntityState.Modified;
        }
    }
}
