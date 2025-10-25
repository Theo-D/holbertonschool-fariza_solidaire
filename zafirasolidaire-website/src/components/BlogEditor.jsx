import React, { useState, useRef } from 'react';
import {
  Editor,
  EditorState,
  RichUtils,
  convertToRaw,
  convertFromRaw,
} from 'draft-js';
import 'draft-js/dist/Draft.css';

const BlogEditor = () => {
  const [editorState, setEditorState] = useState(() =>
    EditorState.createEmpty()
  );

  const editorRef = useRef(null);

  // Handle inline styles (bold, italic, underline, etc.)
  const handleKeyCommand = (command, editorState) => {
    const newState = RichUtils.handleKeyCommand(editorState, command);
    if (newState) {
      setEditorState(newState);
      return 'handled';
    }
    return 'not-handled';
  };

  // Example of toggling inline styles manually
  const toggleInlineStyle = (style) => {
    setEditorState(RichUtils.toggleInlineStyle(editorState, style));
  };

  // Get editor content as JSON
  const getContentAsJSON = () => {
    const content = editorState.getCurrentContent();
    return JSON.stringify(convertToRaw(content), null, 2);
  };

  return (
    <div style={styles.container}>
      <div style={styles.toolbar}>
        <button onClick={() => toggleInlineStyle('BOLD')}>Bold</button>
        <button onClick={() => toggleInlineStyle('ITALIC')}>Italic</button>
        <button onClick={() => toggleInlineStyle('UNDERLINE')}>Underline</button>
      </div>

      <div style={styles.editor} onClick={() => editorRef.current?.focus()}>
        <Editor
          ref={editorRef}
          editorState={editorState}
          handleKeyCommand={handleKeyCommand}
          onChange={setEditorState}
          placeholder="Start typing here..."
        />
      </div>

      <pre style={styles.output}>{getContentAsJSON()}</pre>
    </div>
  );
};

const styles = {
  container: { border: '1px solid #ccc', padding: 20, borderRadius: 8 },
  toolbar: { marginBottom: 10 },
  editor: {
    border: '1px solid #ddd',
    minHeight: 120,
    padding: 10,
    cursor: 'text',
  },
  output: {
    backgroundColor: '#f9f9f9',
    padding: 10,
    marginTop: 20,
    borderRadius: 4,
    fontSize: 12,
  },
};

export default BlogEditor;
