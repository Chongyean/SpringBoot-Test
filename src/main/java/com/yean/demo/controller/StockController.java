package com.yean.demo.controller;

public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listStocks() {
        return stockService.listStocks();
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createStock(@RequestBody StockModel payload) {
        return stockService.createStock(payload);
    }

    @PatchMapping("{id}")
    public ResponseEntity<BaseResponseModel> adjustQuantity(@PathVariable("id") Long stockId,@RequestBody UpdateStockModel payload) {
        return stockService.adjustQuantity(stockId,payload);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponseModel> deleteStock(@PathVariable("id") Long stockId) {
        return stockService.deleteStock(stockId);
    }
}
