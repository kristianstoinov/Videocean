package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TOVA TRQBVA LI IZOBSHTO?!
public class Comment {

	private int commentID;
	private Clip thisClip;
	private String commentDescription;
	private List<Comment> answerComments;

	public Comment(int commentID, Clip thisClip, String commentDescription) {
		this.commentID = commentID;
		this.thisClip = thisClip;
		this.commentDescription = commentDescription;
		this.answerComments = new ArrayList<Comment>();
	}
//PRoverkI?!
	public Comment(int commentID, int clipID, String commentDescription) {
		this.commentID = commentID;
		
		
		
	}

	public void addAnswerComment(Comment comment) {
		if (comment != null)
			this.answerComments.add(comment);
	}

	public void removeAnswerComment(Comment comment) {
		if (comment != null) {
			if (answerComments.contains(comment)) {
				this.answerComments.remove(comment);
			}
		}
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public Clip getThisClip() {
		return thisClip;
	}

	public void setThisClip(Clip thisClip) {
		this.thisClip = thisClip;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public List<Comment> getAnswerComments() {
		return (List<Comment>) Collections.unmodifiableCollection(answerComments);
	}

}
