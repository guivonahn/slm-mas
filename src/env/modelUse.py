from flask import Flask, request, jsonify
from transformers import MarianMTModel, MarianTokenizer

model_path = "C:\\Users\\guilh\\OneDrive\\Desktop\\slmAgent\\slmAgentMAS\\src\\env\\opus-en-to-pt-translator"
tokenizer = MarianTokenizer.from_pretrained(model_path)
model = MarianMTModel.from_pretrained(model_path)

app = Flask(__name__)

def translate_sentence(sentence: str) -> str:
    sentence = sentence.strip()
    inputs = tokenizer(sentence, return_tensors="pt", padding=True, truncation=True)
    translated = model.generate(**inputs)
    decode = tokenizer.decode(translated[0], skip_special_tokens=True)


    print("[PYTHON]: translate_sentence called")

    return decode

@app.route("/translate", methods=["POST"])
def translate():
    data = request.get_json()

    sentence = data["sentence"]
    result = translate_sentence(sentence)

    return result

if __name__ == "__main__":
    app.run(debug=True, port=8000)