import React, { useState, useEffect } from "react";
import './css/ProductDetailsPage.css';
import { useParams, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
export default function ProductDetailsPage() {
    const { productId } = useParams();
    const navigate = useNavigate(); 
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [wishlistProducts, setWishlistProducts] = useState([]);
    const currentUserId = Number(localStorage.getItem("benutzerId"));
    console.log("Current user ID from localStorage:", currentUserId); 
    const fetchProductDetails = async () => {
        const token = localStorage.getItem("token");
        try {
            const response = await fetch(`http://localhost:8080/api/v1/produkte/${productId}`, {
                headers: {
                    Authorization: "Bearer " + token,
                }
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log("Fetched product data:", data); 
            setProduct(data);
        } catch (error) {
            console.error("Error fetching product details:", error);
        } finally {
            setLoading(false);
        }
    };
    const addToShoppingList = async (productId) => {
        const benutzerId = localStorage.getItem("benutzerId");
        const token = localStorage.getItem("token");
    
        try {
            const response = await fetch('http://localhost:8080/api/v1/produkte/addToShoppinglist', {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + token,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ benutzerId, produktId: productId }), 
            });
    
            if (!response.ok) {
                throw new Error("Error adding product to shopping list");
            }
    
            const result = await response.json();
            console.log("Product added to shopping list:", result);
            toast.success("Product added to shopping list");
        setTimeout(() => {
          navigate("/marktplatz");
        }, 2500);
        } catch (error) {
            console.error("Error adding to shopping list:", error);
        }
    };

    const fetchWishlistProducts = async () => {
        const benutzerId = localStorage.getItem("benutzerId");
        const token = localStorage.getItem("token");
        if (!benutzerId || !token) {
            console.error("User ID or token is missing in localStorage");
            return;
        }

        try {
            const response = await fetch(
                `http://localhost:8080/api/v1/product/AddToWishlist/user/${benutzerId}`,
                {
                    headers: {
                        Authorization: "Bearer " + token,
                    },
                }
            );

            if (!response.ok) {
                throw new Error("Network response was not ok");
            }

            const wishlistData = await response.json();
            setWishlistProducts(wishlistData || []);
        } catch (error) {
            console.error("Error fetching wishlist products:", error);
        }
    };

    const isProductInWishlist = (productId) => {
        return wishlistProducts.some((product) => product.produktId === productId);
    };

    const addToWishlist = async (productId) => {
        const benutzerId = localStorage.getItem("benutzerId");
        const token = localStorage.getItem("token");
        if (isProductInWishlist(productId)) {
            console.log("Product is already in wishlist");
            return;
        }

        try {
            const response = await fetch(
                `http://localhost:8080/api/v1/product/addToWishlist/${productId}`,
                {
                    method: "POST",
                    headers: {
                        Authorization: "Bearer " + token,
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ benutzerId }),
                }
            );

            if (!response.ok) {
                throw new Error("Error adding product to wishlist");
            }

            const newProduct = await response.json();
            setWishlistProducts((prev) => [...prev, newProduct]);
            console.log("Added to wishlist:", newProduct);
        } catch (error) {
            console.error("Error adding to wishlist:", error);
        }
    };

    useEffect(() => {
        fetchProductDetails();
        fetchWishlistProducts();
    }, [productId]);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (!product) {
        return <p>Produkt nicht gefunden.</p>;
    }

    const handleEdit = () => {
        console.log("Bearbeiten Button clicked");
        navigate(`/updateproduct/${productId}`); 
    };

    const handleSold = () => {
        console.log("Verkauft Button clicked");
    };
    const handlePurchase = () => {
        addToShoppingList(product.id); 

    };
    const handleWishlist = () => {
        console.log("Auf die Wunschliste Button clicked");
    };
    
    const isOwner = product.benutzerId === currentUserId; 
    console.log("Current user ID from localStorage (as number):", currentUserId);
    console.log("Product owner ID:", product.benutzerId);
    console.log("Is current user the owner?", isOwner);
    return (
        <section className="background_section_details">
            <div className="product-details">
                <div className="content">
                    <div className="product-image">
                        <img src={product.imgUrl} alt={product.titel} />
                        <div className="product-buttons">
                        {isOwner ? (
                                <>
                                    <button onClick={handleEdit} className="edit-button">Bearbeiten</button>
                                    {/* <button onClick={handleSold} className="sold-button">Verkauft</button> */}
                                </>
                            ) : (
                                <button onClick={handlePurchase} className="sold-button">Kaufen</button>
                            )}
                        </div>
                    </div>
                </div>
                <div className="product-info">
                    <h1>{product.titel}</h1>
                    <p className="product-price">{product.preis.toFixed(2)} EUR</p>
                    <p><span className="info-product-span">Zustand:</span> {product.zustand}</p>
                    <p className="info-product-p"><span className="info-product-span">Marke:</span> {product.marke}</p>
                    <p><span className="info-product-span">Lieferung:</span> {product.lieferung ? "Ja" : "Nein"}</p>
                    <p><span className="info-product-span">Anzahl:</span> {product.anzahl} stk.</p>
                    <label className="wishlist_label_details">
                        {!isProductInWishlist(product.id) ? (
                            <button onClick={() => addToWishlist(product.id)}>
                                Auf WunschListe ❤️
                            </button>
                        ) : (
                            <span></span>
                        )}
                    </label>
                    <h2><span className="info-product-span">Produktbeschreibung</span></h2>
                    <p className="product-description">{product.beschreibung}</p>
                </div>
            </div>
            <ToastContainer />
        </section>
    );
}