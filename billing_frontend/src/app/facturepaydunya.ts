export class FacturePaydunya{

    
    
    invoice? : Invoice
    store?: Store
    actions?: actions
    items?: items

    constructor (invoice : Invoice, store: Store , actions : actions,items: items){
        this.invoice = invoice;
        this.store = store;
        this.actions = actions;
        this.items = items;
    }

    
}

class Invoice{
    items? : Object
    total_amount?: number
    description?: String
    
}

class Store{
    name?: String
    tagline?: String
    postal_address?: String
    phone?: String
    
}

class actions{
    cancel_url?: string
    return_url?: string
    callback_url?: string
}

class items{
    name?: string
    quantity?: number
    unit_price?: string
    total_price?: string
    description?: string

}

export interface CheckoutResponse{

    response_code ?: string 
    response_text ?: string
    description ?: string
    token ?: string

}