package database.dao;

import java.util.ArrayList;

public interface IRepository<TEntity> {

	void add(TEntity entity);
	void update(TEntity entity);
	void delete(TEntity entity);
	TEntity getById(int entityId);
	ArrayList<TEntity> getAll(int limit);
}
