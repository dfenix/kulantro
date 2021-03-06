package examples.shoppingCart.actions

import constants.ActionTypes
import examples.shoppingCart.api.Product
import examples.shoppingCart.api.products
import examples.shoppingCart.reducers.CartAction
import examples.shoppingCart.reducers.ProductsState
import redux.Action
import redux.Provider.store
import java.util.*
import kotlin.concurrent.schedule

class ActionWithProducts(override val type: String, val products: List<Product>) : Action

fun receiveProducts(products: List<Product>) = ActionWithProducts(ActionTypes.RECEIVE_PRODUCTS, products)

fun getAllproducts() {
    Timer("receive products").schedule(1000) {
        store.dispatch(receiveProducts(products()))
    }
}

fun addToCartUnsafe(productId: Int): Action {
    return CartAction(ActionTypes.ADD_TO_CART, productId)
}

fun addToCart(productId: Int) {
    val state = store.getStateFor("products")
    if (state is ProductsState && state.byId[productId]?.inventory!! > 0) {
        store.dispatch(addToCartUnsafe(productId))
    }
}