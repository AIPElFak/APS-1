package database.dao;

import java.util.ArrayList;

public interface IRepository<TEntity> {

	boolean add(TEntity entity);
	boolean update(TEntity entity);
	boolean delete(TEntity entity);
	TEntity getById(int entityId);
	ArrayList<TEntity> getAll(int limit);
}
