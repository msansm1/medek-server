package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Editor;

/**
 * DAO for EDITOR table
 * 
 * @author msansm1
 *
 */
@Stateless
public class EditorDAO extends Dao {

	public void updateEditor(Editor editor) {
		em.merge(editor);
	}

	public void saveEditor(Editor editor) {
		em.persist(editor);
		em.refresh(editor);
	}

	public void removeEditor(Editor editor) {
		em.remove(em.merge(editor));
		em.flush();
	}

	public List<Editor> getEditors() {
		return em.createQuery("from Editor", Editor.class)
				.getResultList();
	}

	public Editor getEditor(Integer id) {
		return em.find(Editor.class, id);
	}
}
