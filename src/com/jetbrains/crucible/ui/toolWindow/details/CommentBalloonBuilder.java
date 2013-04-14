package com.jetbrains.crucible.ui.toolWindow.details;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.*;
import com.intellij.ui.awt.RelativePoint;
import com.jetbrains.crucible.model.Comment;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * User: ktisha
 */
public class CommentBalloonBuilder {

  public CommentBalloonBuilder() {
  }

  public void showBalloon(@NotNull final Comment comment, @NotNull final Editor editor,
                          @NotNull final CommentsTree balloonContent) {

    final Document document = editor.getDocument();
    final int startOffset = document.getLineStartOffset(Integer.parseInt(comment.getLine()));

    final ComponentPopupBuilder builder = JBPopupFactory.getInstance().
      createComponentPopupBuilder(balloonContent, balloonContent);
    builder.setResizable(true);
    builder.setTitle("Comments");
    builder.setMovable(true);
    final JBPopup popup = builder.createPopup();
    final Point targetPoint = editor.visualPositionToXY(editor.offsetToVisualPosition(startOffset));
    popup.show(new RelativePoint(editor.getComponent(), targetPoint));
  }


  public Balloon getNewCommentBalloon(final CommentForm balloonContent, @NotNull final String title) {
    final BalloonBuilder balloonBuilder =
      JBPopupFactory.getInstance().
        createDialogBalloonBuilder(balloonContent, title);
    balloonBuilder.setHideOnClickOutside(true);
    balloonBuilder.setHideOnKeyOutside(true);

    return balloonBuilder.createBalloon();
  }

}
