import React, { useState } from 'react';

function SneakerImage({ src, alt, className }) {
    const [errored, setErrored] = useState(false);

    if (!src || errored) {
        return <div className={`sneaker-img-placeholder ${className || ''}`}>No image</div>;
    }

    return (
        <img
            src={src}
            alt={alt}
            className={className}
            onError={() => setErrored(true)}
        />
    );
}

export default SneakerImage;
