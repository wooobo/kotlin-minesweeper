package minesweeper

sealed class Cell(val position: Position) {
    abstract val isMine: Boolean
    abstract val mineCount: Int

    val x: Int
        get() = position.x

    open fun determineCell(determineMineCount: Int) {}

    fun matchRowIndex(rowIndex: Int): Boolean {
        return position.y == rowIndex
    }

    data class MineCell(val pos: Position) : Cell(pos) {
        override val isMine: Boolean = true
        override val mineCount: Int = 0
    }

    data class NumberCell(val pos: Position, private var _mineCount: Int = 0) : Cell(pos) {
        override val isMine: Boolean = false
        override val mineCount: Int
            get() = _mineCount

        override fun determineCell(determineMineCount: Int) {
            _mineCount = determineMineCount
        }
    }

    companion object {
        fun createMine(position: Position): Cell {
            return MineCell(position)
        }

        fun createDefault(position: Position): Cell {
            return NumberCell(position)
        }
    }
}
