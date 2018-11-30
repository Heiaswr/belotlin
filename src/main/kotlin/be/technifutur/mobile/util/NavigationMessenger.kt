package be.technifutur.mobile.util

object NavigationMessenger {

    private val listeners = mutableListOf<Listener>()

    fun navigateTo(page: Page, data: Any? = null) {
        var i: Int = 0

        while (i < listeners.size) {
            val listener = listeners[i]
            listener.onNavigateTo(page, data)
            i+=1
        }
    }

    fun register(listener: Listener) {
        this.listeners.add(listener)
    }

    interface Listener {

        fun onNavigateTo(page: Page, data: Any?)
    }
}
