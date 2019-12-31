<template>
  <div :id="id"></div>
</template>

<script>
import 'codemirror/lib/codemirror.css'
import 'tui-editor/dist/tui-editor.css'
import 'tui-editor/dist/tui-editor-contents.css'

import Editor from 'tui-editor'
import defaultOptions from './options'

export default {
  name: 'Markdown',
  props: {
    id: {
      type: String,
      required: false,
      default () {
        return 'markdown-editor-' + new Date() + ((Math.random() * 1000).toFixed(0) + '')
      }
    },
    value: {
      type: String,
      default: ''
    },
    options: {
      type: Object,
      default () {
        return defaultOptions
      }
    },
    mode: {
      type: String,
      default: 'markdown'
    },
    height: {
      type: String,
      required: false,
      default: '300px'
    },
    language: {
      type: String,
      required: false,
      default: 'en_US'
    }
  },
  data () {
    return {
      editor: null
    }
  },
  computed: {
    editorOptions () {
      const options = Object.assign({}, defaultOptions, this.options)
      options.initalEditType = this.mode
      options.height = this.height
      options.language = this.language

      return options
    }
  },
  watch: {
    value (newValue, oldValue) {
      if (newValue !== oldValue && newValue !== this.editor.getValue()) {
        this.editor.setValue(newValue)
      }
    },
    language (val) {
      this.destroyEditor()
      this.initEdotor()
    },
    height (val) {
      this.editor.height(val)
    },
    mode (val) {
      this.editor.changeMode(val)
    }
  },
  methods: {
    initEdotor () {
      this.editor = new Editor({
        el: document.getElementById(this.id),
        ...this.editorOptions
      })

      if (this.value) {
        this.editor.setValue(this.value)
      }

      this.editor.on('change', () => {
        this.$emit('input', this.editor.getValue())
      })
    },
    destroyEditor () {
      if (!this.editor) {
        return
      }

      this.editor.off('change')
      this.editor.remove()
    },
    getValue () {
      return this.editor.getValue()
    },
    setValue (value) {
      this.editor.setValue(value)
    },
    getHtml () {
      return this.editor.getHtml()
    },
    setHtml (value) {
      this.editor.setHtml(value)
    }
  },
  mounted () {
    this.initEdotor()
  },
  destroyed () {
    this.destroyEditor()
  }
}
</script>
