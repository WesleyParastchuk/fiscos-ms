package main

import (
	"fmt"
	"log"
	"os"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()

	router.GET("/hello", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "Hello gat",
		})
	})

	port := os.Getenv("PORT")
	if port == "" {
		port = "3000"
	}

	addr := fmt.Sprintf("0.0.0.0:%s", port)
	log.Printf("gateway listening on %s", addr)

	if err := router.Run(addr); err != nil {
		log.Fatalf("failed to start gateway: %v", err)
	}
}
